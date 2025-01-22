import ProductCard from "../ProductCard";
import { Product } from "../../types/Product";
import "./styles.css";
import { useContext } from "react";
import { AuthContext } from "../../contexts/Auth/AuthContext";

interface ProductListProps {
  products: Product[] | undefined;
  isFavoritePage: boolean;
  onToggleFavorite: (product: Product) => void;
}

const ProductList: React.FC<ProductListProps> = ({
  products,
  isFavoritePage,
  onToggleFavorite
}) => {
  const auth = useContext(AuthContext);

  return (
    <div>
      {isFavoritePage && auth.client?.favoriteList?.title && (
        <div style={{ textAlign: "start", marginBottom: "16px"}}>
          <h2>{`${auth.client.favoriteList.title}`}</h2>
        </div>
      )}
      <>
        <div className="product-list">
          {!products || products?.length === 0 ? (
            <p>
              {isFavoritePage
                ? "Você ainda não possui favoritos."
                : "Não há itens para serem exibidos."}
            </p>
          ) : (
            products?.map((product) => (
              <ProductCard
                key={product.id}
                product={product}
                onToggleFavorite={onToggleFavorite}
                buttonText={isFavoritePage ? "Desfavoritar" : "Favoritar"}
              />
            ))
          )}
        </div>
      </>
    </div>
  );
};

export default ProductList;
