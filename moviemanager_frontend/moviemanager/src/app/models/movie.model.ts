import { Actor } from "./actor.model";
import { Director } from "./director.model";

export interface Movie {
    id?: number;
    title: string;
    productionYear: number;
    description: string;
    imgPath?: string;
    categoryId?: number;
    directorId?: number;
    director?: Director;
    actors?: Actor[];
}
