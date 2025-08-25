import React, { useState, useContext } from "react";
import axios from "axios";
import { AuthContext } from "./AuthContext";
import { useNavigate } from "react-router-dom";

function Login() {
  const { login } = useContext(AuthContext);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async () => {
    const res = await axios.post("http://localhost:8080/api/auth/login", { email, password });
    if (res.data.token) {
      login(res.data.token);
      navigate("/dashboard");
    } else {
      alert("Invalid credentials");
    }
  };

  return (
    <div className="flex flex-col items-center justify-center h-screen bg-gray-900 text-white">
      <h1 className="text-3xl mb-4">Login</h1>
      <input className="mb-2 p-2 text-black" placeholder="Email" value={email} onChange={e => setEmail(e.target.value)} />
      <input className="mb-2 p-2 text-black" placeholder="Password" type="password" value={password} onChange={e => setPassword(e.target.value)} />
      <button onClick={handleLogin} className="bg-blue-500 px-4 py-2 rounded">Login</button>
    </div>
  );
}

export default Login;
