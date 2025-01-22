import React from 'react';
import "./styles.css"
import { Product } from '../../types/Product';

interface ProductCardProps {
  product: Product;
  onToggleFavorite: (product: Product) => void;
  buttonText: string;
}

const ProductCard: React.FC<ProductCardProps> = ({ product, onToggleFavorite, buttonText }) => {
  const{title, image, price} = product;

  return (
    <div className="product-card">
      <img src={image} alt={title} className="product-image" />
      <h3 className="product-title">{title}</h3>
      <p className="product-price">${price}</p>
      <button className="product-button" onClick={() => onToggleFavorite(product)}>{buttonText}</button>
    </div>
  );
};

export default ProductCard;
