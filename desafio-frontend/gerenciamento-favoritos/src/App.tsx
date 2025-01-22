import './App.css'
import { Route, Routes } from 'react-router-dom'
import AuthForm from './components/AuthForm'
import { RequireAuth } from './contexts/Auth/RequireAuth'
import Menu from './pages/Menu'

function App() {

  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<AuthForm isLogin={true} /> } />
        <Route path="/signin" element={<AuthForm isLogin={true} /> } />
        <Route path="/signup" element={<AuthForm isLogin={false} /> } />
        <Route path="/menu" element={<RequireAuth><Menu /></RequireAuth>} />
      </Routes>
    </div>
  )
}

export default App
