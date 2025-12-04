import React, {useState} from 'react';
import Products from './pages/Products';
import Login from './pages/Login';

export default function App(){
  const [authed, setAuthed] = useState(!!localStorage.getItem('accessToken'));
  return (
    <div>
      {authed ? <Products/> : <Login onLogin={() => setAuthed(true)}/>}
    </div>
  );
}

