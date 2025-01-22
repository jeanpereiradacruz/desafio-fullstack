import { Client } from "./Client";
import { Product } from "./Product";

export type FavoriteList = {
    id: string,
    title: string,
    description: string,
    client?: Client,
    products: Product[]
}
