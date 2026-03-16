const API = 'http://localhost:8080';
let token = null;
let currentUser = null;
let tableData = {};

// ── AUTH ──
document.getElementById('password').addEventListener('keydown', e => {
  if (e.key === 'Enter') doLogin();
});

document.getElementById('btn-login').addEventListener('click', doLogin);

async function doLogin() {
  const username = document.getElementById('username').value.trim();
  const password = document.getElementById('password').value;
  const btn = document.getElementById('btn-login');
  const err = document.getElementById('login-error');

  if (!username || !password) {
    showLoginError('Ingresa usuario y contraseña');
    return;
  }

  btn.disabled = true;
  btn.textContent = 'AUTENTICANDO...';
  err.style.display = 'none';

  try {
    const res = await fetch(`${API}/auth/login`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, password })
    });

    if (!res.ok) throw new Error('Credenciales inválidas');

    const data = await res.json();
    token = data.token;
    currentUser = username;

    document.getElementById('user-name').textContent = username;
    document.getElementById('user-avatar').textContent = username[0].toUpperCase();

    document.getElementById('login-screen').style.display = 'none';
    document.getElementById('app').style.display = 'flex';

    loadDashboard();

  } catch (e) {
    showLoginError(e.message || 'Error de conexión con el servidor');
  } finally {
    btn.disabled = false;
    btn.textContent = 'INGRESAR AL SISTEMA';
  }
}

function showLoginError(msg) {
  const err = document.getElementById('login-error');
  err.textContent = '⚠ ' + msg;
  err.style.display = 'block';
}

function logout() {
  token = null;
  currentUser = null;
  tableData = {};
  document.getElementById('app').style.display = 'none';
  document.getElementById('login-screen').style.display = 'flex';
  document.getElementById('username').value = '';
  document.getElementById('password').value = '';
}

// ── API helper ──
async function api(path, method = 'GET', body = null) {
  const opts = {
    method,
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    }
  };
  if (body) opts.body = JSON.stringify(body);
  const res = await fetch(API + path, opts);
  if (!res.ok) {
    const msg = await res.text();
    throw new Error(msg || `Error ${res.status}`);
  }
  if (res.status === 204) return null;
  return res.json();
}

// ── NAV ──
document.querySelectorAll('.nav-item').forEach(item => {
  item.addEventListener('click', () => {
    const sec = item.dataset.section;
    document.querySelectorAll('.nav-item').forEach(n => n.classList.remove('active'));
    document.querySelectorAll('.section').forEach(s => s.classList.remove('active'));
    item.classList.add('active');
    document.getElementById(`section-${sec}`).classList.add('active');
    const info = sections[sec];
    document.getElementById('section-title').textContent = info.title;
    document.getElementById('section-sub').textContent = info.sub;
    renderTopbarActions(sec);
    loadSection(sec);
  });
});

const sections = {
  dashboard:   { title: 'Dashboard',    sub: '// Resumen general del sistema' },
  bodegas:     { title: 'Bodegas',      sub: '// Gestión de bodegas' },
  productos:   { title: 'Productos',    sub: '// Catálogo de productos' },
  movimientos: { title: 'Movimientos',  sub: '// Historial de movimientos' },
  usuarios:    { title: 'Usuarios',     sub: '// Gestión de usuarios' },
  auditorias:  { title: 'Auditorías',   sub: '// Registro de cambios' }
};

function renderTopbarActions(sec) {
  const c = document.getElementById('topbar-actions');
  c.innerHTML = '';
  if (sec === 'bodegas')     c.innerHTML = `<button class="btn-sm accent" onclick="openModal('bodega')">+ Nueva Bodega</button>`;
  else if (sec === 'productos')   c.innerHTML = `<button class="btn-sm accent" onclick="openModal('producto')">+ Nuevo Producto</button>`;
  else if (sec === 'movimientos') c.innerHTML = `<button class="btn-sm accent" onclick="openModal('movimiento')">+ Nuevo Movimiento</button>`;
  else if (sec === 'usuarios')    c.innerHTML = `<button class="btn-sm accent" onclick="openModal('usuario')">+ Nuevo Usuario</button>`;
}

function loadSection(sec) {
  if (sec === 'dashboard')   loadDashboard();
  else if (sec === 'bodegas')     loadBodegas();
  else if (sec === 'productos')   loadProductos();
  else if (sec === 'movimientos') loadMovimientos();
  else if (sec === 'usuarios')    loadUsuarios();
  else if (sec === 'auditorias')  loadAuditorias();
}

// ── DASHBOARD ──
async function loadDashboard() {
  try {
    const [bodegas, productos, movimientos, usuarios] = await Promise.allSettled([
      api('/bodegas'), api('/productos'), api('/movimientos'), api('/usuarios')
    ]);

    const b = bodegas.value || [];
    const p = productos.value || [];
    const m = movimientos.value || [];
    const u = usuarios.value || [];

    document.getElementById('stat-bodegas').textContent = b.length;
    document.getElementById('stat-productos').textContent = p.length;
    document.getElementById('stat-movimientos').textContent = m.length;
    document.getElementById('stat-usuarios').textContent = u.length;

    const recent = m.slice(-8).reverse();
    const container = document.getElementById('dash-movimientos');

    if (!recent.length) {
      container.innerHTML = '<div class="empty">Sin movimientos registrados</div>';
      return;
    }

    container.innerHTML = `
      <table>
        <thead>
          <tr><th>ID</th><th>Fecha</th><th>Tipo</th><th>Usuario</th><th>Bodega Origen</th></tr>
        </thead>
        <tbody>
          ${recent.map(mov => `
            <tr>
              <td><span style="font-family:var(--font-mono);color:var(--muted)">#${mov.id}</span></td>
              <td style="font-family:var(--font-mono);font-size:12px">${formatDate(mov.fecha)}</td>
              <td><span class="badge ${mov.tipo.toLowerCase()}">${mov.tipo}</span></td>
              <td>${mov.usuario?.nombreUsuario || '—'}</td>
              <td>${mov.bodegaOrigen?.nombre || '—'}</td>
            </tr>`).join('')}
        </tbody>
      </table>`;
  } catch (e) {
    document.getElementById('dash-movimientos').innerHTML = `<div class="empty">Error: ${e.message}</div>`;
  }
}

// ── BODEGAS ──
async function loadBodegas() {
  const container = document.getElementById('table-bodegas');
  try {
    const data = await api('/bodegas');
    tableData.bodegas = data;
    renderBodegas(data);
  } catch (e) {
    container.innerHTML = `<div class="empty">Error: ${e.message}</div>`;
  }
}

function renderBodegas(data) {
  const container = document.getElementById('table-bodegas');
  if (!data.length) { container.innerHTML = '<div class="empty">Sin bodegas registradas</div>'; return; }
  container.innerHTML = `
    <table>
      <thead><tr><th>ID</th><th>Nombre</th><th>Ubicación</th><th>Capacidad</th><th>Encargado</th><th>Acciones</th></tr></thead>
      <tbody>
        ${data.map(b => `
          <tr>
            <td><span style="font-family:var(--font-mono);color:var(--muted)">#${b.id}</span></td>
            <td><strong>${b.nombre}</strong></td>
            <td>${b.ubicacion}</td>
            <td style="font-family:var(--font-mono)">${b.capacidad?.toLocaleString()}</td>
            <td>${b.encargado ? `${b.encargado.nombre} ${b.encargado.apellido}` : '—'}</td>
            <td><button class="action-btn del" onclick="deleteBodega(${b.id})">Eliminar</button></td>
          </tr>`).join('')}
      </tbody>
    </table>`;
}

async function deleteBodega(id) {
  if (!confirm('¿Eliminar esta bodega?')) return;
  try {
    await api(`/bodegas/${id}`, 'DELETE');
    showToast('Bodega eliminada');
    loadBodegas();
  } catch (e) { showToast(e.message, true); }
}

// ── PRODUCTOS ──
async function loadProductos() {
  const container = document.getElementById('table-productos');
  try {
    const data = await api('/productos');
    tableData.productos = data;
    renderProductos(data);
  } catch (e) {
    container.innerHTML = `<div class="empty">Error: ${e.message}</div>`;
  }
}

function renderProductos(data) {
  const container = document.getElementById('table-productos');
  if (!data.length) { container.innerHTML = '<div class="empty">Sin productos</div>'; return; }
  container.innerHTML = `
    <table>
      <thead><tr><th>ID</th><th>Nombre</th><th>Categoría</th><th>Precio</th><th>Acciones</th></tr></thead>
      <tbody>
        ${data.map(p => `
          <tr>
            <td><span style="font-family:var(--font-mono);color:var(--muted)">#${p.id}</span></td>
            <td><strong>${p.nombre}</strong></td>
            <td>${p.categoria?.nombre || '—'}</td>
            <td style="font-family:var(--font-mono);color:var(--green)">$${parseFloat(p.precio || 0).toLocaleString()}</td>
            <td><button class="action-btn del" onclick="deleteProducto(${p.id})">Eliminar</button></td>
          </tr>`).join('')}
      </tbody>
    </table>`;
}

async function deleteProducto(id) {
  if (!confirm('¿Eliminar este producto?')) return;
  try {
    await api(`/productos/${id}`, 'DELETE');
    showToast('Producto eliminado');
    loadProductos();
  } catch (e) { showToast(e.message, true); }
}

// ── MOVIMIENTOS ──
async function loadMovimientos() {
  const container = document.getElementById('table-movimientos');
  try {
    const data = await api('/movimientos');
    tableData.movimientos = data;
    renderMovimientos(data);
  } catch (e) {
    container.innerHTML = `<div class="empty">Error: ${e.message}</div>`;
  }
}

function renderMovimientos(data) {
  const container = document.getElementById('table-movimientos');
  if (!data.length) { container.innerHTML = '<div class="empty">Sin movimientos</div>'; return; }
  const sorted = [...data].reverse();
  container.innerHTML = `
    <table>
      <thead><tr><th>ID</th><th>Fecha</th><th>Tipo</th><th>Usuario</th><th>Origen</th><th>Destino</th></tr></thead>
      <tbody>
        ${sorted.map(m => `
          <tr>
            <td><span style="font-family:var(--font-mono);color:var(--muted)">#${m.id}</span></td>
            <td style="font-family:var(--font-mono);font-size:12px">${formatDate(m.fecha)}</td>
            <td><span class="badge ${m.tipo.toLowerCase()}">${m.tipo}</span></td>
            <td>${m.usuario?.nombreUsuario || '—'}</td>
            <td>${m.bodegaOrigen?.nombre || '—'}</td>
            <td>${m.bodegaDestino?.nombre || '—'}</td>
          </tr>`).join('')}
      </tbody>
    </table>`;
}

// ── USUARIOS ──
async function loadUsuarios() {
  const container = document.getElementById('table-usuarios');
  try {
    const data = await api('/usuarios');
    tableData.usuarios = data;
    renderUsuarios(data);
  } catch (e) {
    container.innerHTML = `<div class="empty">Error: ${e.message}</div>`;
  }
}

function renderUsuarios(data) {
  const container = document.getElementById('table-usuarios');
  if (!data.length) { container.innerHTML = '<div class="empty">Sin usuarios</div>'; return; }
  container.innerHTML = `
    <table>
      <thead><tr><th>ID</th><th>Usuario</th><th>Nombre</th><th>Rol</th><th>Email</th><th>Acciones</th></tr></thead>
      <tbody>
        ${data.map(u => `
          <tr>
            <td><span style="font-family:var(--font-mono);color:var(--muted)">#${u.id}</span></td>
            <td style="font-family:var(--font-mono)">${u.nombreUsuario}</td>
            <td>${u.persona ? `${u.persona.nombre} ${u.persona.apellido}` : '—'}</td>
            <td><span class="badge ${u.rol?.nombre?.toLowerCase() || 'empleado'}">${u.rol?.nombre || '—'}</span></td>
            <td style="color:var(--muted);font-size:12px">${u.persona?.email || '—'}</td>
            <td><button class="action-btn del" onclick="deleteUsuario(${u.id})">Eliminar</button></td>
          </tr>`).join('')}
      </tbody>
    </table>`;
}

async function deleteUsuario(id) {
  if (!confirm('¿Eliminar este usuario?')) return;
  try {
    await api(`/usuarios/${id}`, 'DELETE');
    showToast('Usuario eliminado');
    loadUsuarios();
  } catch (e) { showToast(e.message, true); }
}

// ── AUDITORIAS ──
async function loadAuditorias() {
  const container = document.getElementById('table-auditorias');
  try {
    const data = await api('/auditorias');
    tableData.auditorias = data;
    renderAuditorias(data);
  } catch (e) {
    container.innerHTML = `<div class="empty">Error: ${e.message}</div>`;
  }
}

function renderAuditorias(data) {
  const container = document.getElementById('table-auditorias');
  if (!data.length) { container.innerHTML = '<div class="empty">Sin registros de auditoría</div>'; return; }
  const sorted = [...data].reverse();
  container.innerHTML = `
    <table>
      <thead><tr><th>ID</th><th>Fecha</th><th>Operación</th><th>Entidad</th><th>Entidad ID</th><th>Usuario</th></tr></thead>
      <tbody>
        ${sorted.map(a => `
          <tr>
            <td><span style="font-family:var(--font-mono);color:var(--muted)">#${a.id}</span></td>
            <td style="font-family:var(--font-mono);font-size:12px">${formatDate(a.fecha)}</td>
            <td><span class="badge ${opBadge(a.operacion)}">${a.operacion}</span></td>
            <td>${a.entidad || '—'}</td>
            <td style="font-family:var(--font-mono)">${a.entidadId || '—'}</td>
            <td>${a.usuario?.nombreUsuario || '—'}</td>
          </tr>`).join('')}
      </tbody>
    </table>`;
}

function opBadge(op) {
  if (op === 'INSERT') return 'entrada';
  if (op === 'DELETE') return 'salida';
  return 'transferencia';
}

// ── SEARCH ──
function filterTable(sec) {
  const q = document.getElementById(`search-${sec}`).value.toLowerCase();
  const data = tableData[sec] || [];
  const filtered = data.filter(item => JSON.stringify(item).toLowerCase().includes(q));
  if (sec === 'bodegas')          renderBodegas(filtered);
  else if (sec === 'productos')   renderProductos(filtered);
  else if (sec === 'movimientos') renderMovimientos(filtered);
  else if (sec === 'usuarios')    renderUsuarios(filtered);
  else if (sec === 'auditorias')  renderAuditorias(filtered);
}

// ── MODALS ──
async function openModal(type) {
  const overlay = document.getElementById('modal-overlay');
  const title   = document.getElementById('modal-title');
  const body    = document.getElementById('modal-body');
  const confirm = document.getElementById('modal-confirm');

  if (type === 'bodega') {
    title.textContent = 'Nueva Bodega';
    body.innerHTML = `
      <div class="field"><label>Nombre</label><input type="text" id="m-nombre" placeholder="Bodega Norte"></div>
      <div class="field"><label>Ubicación</label><input type="text" id="m-ubicacion" placeholder="Bogotá"></div>
      <div class="field"><label>Capacidad</label><input type="number" id="m-capacidad" placeholder="1000"></div>
      <div class="field"><label>ID Encargado</label><input type="number" id="m-encargado" placeholder="1"></div>`;
    confirm.onclick = async () => {
      try {
        await api('/bodegas', 'POST', {
          nombre: val('m-nombre'), ubicacion: val('m-ubicacion'),
          capacidad: parseInt(val('m-capacidad')), encargadoId: parseInt(val('m-encargado'))
        });
        closeModal(); showToast('Bodega creada'); loadBodegas();
      } catch(e) { showToast(e.message, true); }
    };
  }

  else if (type === 'producto') {
    let cats = [];
    try { cats = await api('/categorias'); } catch(e) {}
    title.textContent = 'Nuevo Producto';
    body.innerHTML = `
      <div class="field"><label>Nombre</label><input type="text" id="m-nombre" placeholder="Laptop Dell"></div>
      <div class="field"><label>Categoría</label>
        <select id="m-categoria">
          ${cats.map(c => `<option value="${c.id}">${c.nombre}</option>`).join('') || '<option>Sin categorías</option>'}
        </select>
      </div>
      <div class="field"><label>Precio</label><input type="number" id="m-precio" placeholder="1500.00" step="0.01"></div>`;
    confirm.onclick = async () => {
      try {
        await api('/productos', 'POST', {
          nombre: val('m-nombre'),
          categoriaId: parseInt(document.getElementById('m-categoria').value),
          precio: parseFloat(val('m-precio'))
        });
        closeModal(); showToast('Producto creado'); loadProductos();
      } catch(e) { showToast(e.message, true); }
    };
  }

  else if (type === 'movimiento') {
    let bodegas = [], usuarios = [];
    try { [bodegas, usuarios] = await Promise.all([api('/bodegas'), api('/usuarios')]); } catch(e) {}
    title.textContent = 'Nuevo Movimiento';
    const bodegaOpts = bodegas.map(b => `<option value="${b.id}">${b.nombre}</option>`).join('');
    body.innerHTML = `
      <div class="field"><label>Tipo</label>
        <select id="m-tipo">
          <option value="ENTRADA">ENTRADA</option>
          <option value="SALIDA">SALIDA</option>
          <option value="TRANSFERENCIA">TRANSFERENCIA</option>
        </select>
      </div>
      <div class="field"><label>Usuario</label>
        <select id="m-usuario">
          ${usuarios.map(u => `<option value="${u.id}">${u.nombreUsuario}</option>`).join('')}
        </select>
      </div>
      <div class="field"><label>Bodega Origen</label><select id="m-origen">${bodegaOpts}</select></div>
      <div class="field"><label>Bodega Destino (opcional)</label>
        <select id="m-destino"><option value="">— Ninguna —</option>${bodegaOpts}</select>
      </div>`;
    confirm.onclick = async () => {
      try {
        const destino = document.getElementById('m-destino').value;
        await api('/movimientos', 'POST', {
          fecha: new Date().toISOString(),
          tipo: document.getElementById('m-tipo').value,
          usuarioId: parseInt(document.getElementById('m-usuario').value),
          bodegaOrigenId: parseInt(document.getElementById('m-origen').value),
          bodegaDestinoId: destino ? parseInt(destino) : null
        });
        closeModal(); showToast('Movimiento registrado'); loadMovimientos();
      } catch(e) { showToast(e.message, true); }
    };
  }

  else if (type === 'usuario') {
    let roles = [];
    try { roles = await api('/roles'); } catch(e) {}
    title.textContent = 'Nuevo Usuario';
    body.innerHTML = `
      <div class="field"><label>Nombre de usuario</label><input type="text" id="m-username" placeholder="jdoe123"></div>
      <div class="field"><label>Contraseña</label><input type="password" id="m-pass" placeholder="••••••••"></div>
      <div class="field"><label>Rol</label>
        <select id="m-rol">
          ${roles.map(r => `<option value="${r.id}">${r.nombre}</option>`).join('') || '<option>Sin roles</option>'}
        </select>
      </div>`;
    confirm.onclick = async () => {
      try {
        await api('/auth/register', 'POST', {
          username: val('m-username'),
          password: val('m-pass'),
          rolId: parseInt(document.getElementById('m-rol').value)
        });
        closeModal(); showToast('Usuario creado'); loadUsuarios();
      } catch(e) { showToast(e.message, true); }
    };
  }

  overlay.classList.add('open');
}

function closeModal(e) {
  if (e && e.target !== document.getElementById('modal-overlay')) return;
  document.getElementById('modal-overlay').classList.remove('open');
}

function val(id) { return document.getElementById(id)?.value || ''; }

// ── UTILS ──
function formatDate(str) {
  if (!str) return '—';
  const d = new Date(str);
  return d.toLocaleDateString('es-CO') + ' ' + d.toLocaleTimeString('es-CO', { hour: '2-digit', minute: '2-digit' });
}

function showToast(msg, isError = false) {
  const t = document.getElementById('toast');
  t.textContent = (isError ? '⚠ ' : '✓ ') + msg;
  t.className = 'toast' + (isError ? ' error' : '') + ' show';
  setTimeout(() => t.classList.remove('show'), 3000);
}
