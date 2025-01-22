import { useContext, useEffect, useState } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import emailValidator from "../../utils/EmailValidator";
import { AuthContext } from "../../contexts/Auth/AuthContext";
import "./styles.css";

const AuthForm = ({ isLogin }: { isLogin: boolean }) => {
  const auth = useContext(AuthContext);
  const location = useLocation();
  const navigate = useNavigate();

  const [errorMessage, setErrorMessage] = useState<string>("");

  const initialConfig = {
    name: "",
    email: "",
    password: ""
  };

  const [client, setClient] = useState(initialConfig);

  const [invalidField, setInvalidField] = useState(initialConfig);

  useEffect(() => {
    setClient(initialConfig);
    setErrorMessage("");
    setInvalidField(initialConfig);
  }, [location.pathname]);

  const handleOnChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setClient((prevClient) => ({
      ...prevClient,
      [name]: value
    }));
  };

  const isDataInvalid = (): boolean => {
    let updateErrorField = false;

    updateErrorField = isLogin
      ? !client.password || !emailValidator(client.email)
      : !client.name || !client.password || !emailValidator(client.email);

    if (updateErrorField) {
      setInvalidField((prev) => ({
        ...prev,
        name: !isLogin && !client.name ? "Preencha o nome" : "",
        password: !client.password ? "Preencha a senha" : "",
        email: !emailValidator(client.email) ? "Insira um e-mail válido" : ""
      }));
    }

    return updateErrorField;
  };

  const handleAuth = async (e: React.FormEvent) => {
    e.preventDefault();

    if (isDataInvalid()) return;

    try {
      const response = isLogin
        ? await auth.signIn(client.email, client.password)
        : await auth.signUp(client.name, client.email, client.password);

      if (response) {
        navigate("/menu");
      }
    } catch (error) {
      if (error instanceof Error) {
        setErrorMessage(error.message);
        setInvalidField(initialConfig);
      }
    }
  };

  return (
    <>
      <div className="login-container">
        <h1>Sistema de Cadastro de Produtos Favoritos</h1>
        <form>
          {!isLogin && (
            <div>
              <input
                type="text"
                id="name"
                name="name"
                placeholder="Digite seu nome"
                onChange={handleOnChange}
                value={client.name}
              />
              {invalidField.name && (
                <p style={{ color: "red", marginTop: 10, fontSize: 12 }}>
                  {invalidField.name}
                </p>
              )}
            </div>
          )}

          <div>
            <input
              type="text"
              id="email"
              name="email"
              placeholder="Digite seu email"
              onChange={handleOnChange}
              value={client.email}
            />
            {invalidField.email && (
              <p style={{ color: "red", marginTop: 10, fontSize: 12 }}>
                {invalidField.email}
              </p>
            )}
          </div>

          <div>
            <input
              type="password"
              id="password"
              name="password"
              placeholder="Digite sua senha"
              onChange={handleOnChange}
              value={client.password}
            />
            {invalidField.password && (
              <p style={{ color: "red", marginTop: 10, fontSize: 12 }}>
                {invalidField.password}
              </p>
            )}
          </div>

          {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}

          <button onClick={handleAuth}>
            {isLogin ? "Logar" : "Cadastrar"}
          </button>
        </form>

        <p className="signup-link">
          {isLogin ? (
            <>
              Não possui cadastro? <Link to="/signup">Cadastre-se</Link>
            </>
          ) : (
            <>
              Já possui cadastro? <Link to="/signin">Faça Login</Link>
            </>
          )}
        </p>
      </div>
    </>
  );
};

export default AuthForm;
