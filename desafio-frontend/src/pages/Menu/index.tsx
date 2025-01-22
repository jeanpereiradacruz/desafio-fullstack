import { useContext, useEffect, useState } from "react";
import { AuthContext } from "../../contexts/Auth/AuthContext";
import axios from "axios";
import "./styles.css";
import { Product } from "../../types/Product";
import ProductList from "../../components/ProductList";
import MenuHeader from "../../components/MenuHeader";
import { FavoriteProductRequest } from "../../types/FavoriteProductRequest";
import LoadingSpinner from "../../components/LoadingSpinner";
import CustomSnackbar from "../../components/CustomSnackBar";

const Menu: React.FC = () => {
  const auth = useContext(AuthContext);

  const [isFavoritePage, setIsFavoritePage] = useState<boolean>(false);
  const [products, setProducts] = useState<Product[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [favorites, setFavorites] = useState<Product[]>(
    auth?.client?.favoriteList?.products || []
  );

  const [openSnackbar, setOpenSnackbar] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState('');
  const [severity, setSeverity] = useState<'success' | 'error' | 'warning'>('success');

  const showSnackbar = (message: string, severity: 'success' | 'error' | 'warning') => {
    setSnackbarMessage(message);
    setSeverity(severity);
    setOpenSnackbar(true);
  };

  const handleCloseSnackbar = () => {
    setOpenSnackbar(false);
  };

  useEffect(() => {
    if (!isFavoritePage && !products?.length) {
      setLoading(true);
      axios
        .get("https://fakestoreapi.com/products")
        .then((response) => {
          setProducts(response.data);
        })
        .catch((error) => {
          console.error("Erro ao buscar os produtos:", error);
        })
        .finally(() => {
          setLoading(false);
        });
    }
  }, [isFavoritePage]);

  useEffect(() => {
    setFavorites(auth?.client?.favoriteList?.products || [])
  }, [auth?.client])

  const handleAddFavorite = async (product: Product) => {
    if (!auth.client?.favoriteList) {
      showSnackbar('Você ainda não possui uma lista. É necessário ter uma pra favoritar os produtos.', 'warning');
      return;
    }

    if (favorites?.find((fav) => fav.id === product.id)) {
      showSnackbar(`Este produto já foi favoritado na lista ${auth.client?.favoriteList?.title}.`, 'error');
      return;
    }

    if (favorites?.length >= 5) {
      showSnackbar(`Você já favoritou o total permitido de 5 produtos na lista ${auth.client?.favoriteList?.title}.`, 'error');
      return;
    }

    let requestDTO: FavoriteProductRequest = {
      clientId: auth.client?.id,
      product: product,
      favoriteListId: auth.client?.favoriteList?.id
    };

    setLoading(true);

    const updatedClient = await auth.addFavoriteProduct(requestDTO);
    setFavorites(updatedClient?.favoriteList?.products);

    setLoading(false);

    showSnackbar('O produto foi favoritado com sucesso.', 'success');
  };

  const handleRemoveFavoriteProduct = async (product: Product) => {
    const updatedFavorites = favorites.filter((fav) => fav.id !== product.id);
    setFavorites(updatedFavorites);

    let dto: FavoriteProductRequest = {
      product: product,
      clientId: auth.client?.id,
      favoriteListId: auth.client?.favoriteList?.id
    }

    setLoading(true);

    const response = await auth.removeFavoriteProduct(dto);
    setFavorites(response?.favoriteList?.products);

    setLoading(false);

    showSnackbar('O produto foi desfavoritado com sucesso.', 'success');
  };

  const toggleList = (page: string) => {
    setIsFavoritePage(page === "favorites");
  };

  return (
    <div style={{ padding: "20px" }}>
      <MenuHeader toggleList={toggleList} />

      <LoadingSpinner isLoading={loading}/>

      <CustomSnackbar
        open={openSnackbar}
        message={snackbarMessage}
        severity={severity}
        handleClose={handleCloseSnackbar}
      />

      <div className="product-container">
        {isFavoritePage ? (
          <ProductList
            products={favorites}
            isFavoritePage={true}
            onToggleFavorite={handleRemoveFavoriteProduct}
          />
        ) : (
          <ProductList
            products={products}
            isFavoritePage={false}
            onToggleFavorite={handleAddFavorite}
          />
        )}
      </div>
    </div>
  );
};

export default Menu;
