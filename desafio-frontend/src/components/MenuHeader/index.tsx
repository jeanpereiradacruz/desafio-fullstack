import "./styles.css";
import { useContext, useState } from "react";
import { FavoriteListModal } from "../FavoriteListModal";
import { AuthContext } from "../../contexts/Auth/AuthContext";
import { useNavigate } from "react-router-dom";

interface MenuHeaderProps {
  toggleList: (page: string) => void;
}

const MenuHeader: React.FC<MenuHeaderProps> = ({ toggleList }) => {
  const navigate = useNavigate();

  const auth = useContext(AuthContext);

  const [isModalOpen, setModalOpen] = useState(false);
  const handleOpenModal = () => setModalOpen(true);
  const handleCloseModal = () => setModalOpen(false);

  const handleLogOut = () => {
    auth.signOut();
    navigate("/signin");
  };

  return (
    <>
      <h1 className="catalog-title">
        Sistema de Cadastro de Produtos Favoritos
      </h1>

      <div className="catalog-links">
        <button className="catalog-link" onClick={() => toggleList("produtos")}>
          Produtos
        </button>

        <button
          className="catalog-link"
          onClick={() => toggleList("favorites")}
        >
          Favoritos
        </button>

        <button className="catalog-link" onClick={handleOpenModal}>
          Gerenciar Lista
        </button>

        <button
          className="catalog-link"
          onClick={handleLogOut}
          style={{ background: "#708090", position: "fixed" }}
        >
          Sair
        </button>
      </div>

      <FavoriteListModal
        isModalOpen={isModalOpen}
        handleCloseModal={handleCloseModal}
      />
    </>
  );
};

export default MenuHeader;
