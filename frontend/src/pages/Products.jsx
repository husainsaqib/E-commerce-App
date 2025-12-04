import React, {useEffect, useState} from 'react';

export default function Products(){
  const [products, setProducts] = useState([]);
  useEffect(()=>{ fetch('http://localhost:8080/api/v1/products').then(r=>r.json()).then(setProducts).catch(console.error); }, []);
  return (
    <div style={{padding:20}}>
      <h2>Products</h2>
      <ul>{products.map(p=> (<li key={p.id}><b>{p.name}</b> - ${p.price} <div>{p.description}</div></li>))}</ul>
    </div>
  );
}

