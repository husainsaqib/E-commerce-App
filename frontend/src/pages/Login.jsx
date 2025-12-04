import React, {useState} from 'react';

export default function Login({onLogin}){
  const [u, setU] = useState('');
  const [p, setP] = useState('');
  async function submit(e){
    e.preventDefault();
    const res = await fetch('http://localhost:8080/api/v1/auth/login', {
      method:'POST', headers:{'Content-Type':'application/json'}, body: JSON.stringify({username:u, password:p})
    });
    if(!res.ok){ alert('login failed'); return; }
    const data = await res.json();
    localStorage.setItem('accessToken', data.accessToken);
    localStorage.setItem('refreshToken', data.refreshToken);
    onLogin();
  }
  return (
    <form onSubmit={submit} style={{padding:20}}>
      <h2>Login</h2>
      <input placeholder="username" value={u} onChange={e=>setU(e.target.value)} /><br/>
      <input placeholder="password" type="password" value={p} onChange={e=>setP(e.target.value)} /><br/>
      <button type="submit">Login</button>
      <p>Use any username registered via /api/v1/auth/register or implement registration.</p>
    </form>
  );
}

