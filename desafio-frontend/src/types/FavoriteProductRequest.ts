import { Product } from "./Product";

export type FavoriteProductRequest = {
    clientId?: string;
    product: Product;
    favoriteListId?: string;
}