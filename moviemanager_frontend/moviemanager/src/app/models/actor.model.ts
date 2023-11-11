import { Movie } from "./movie.model";

export interface Actor {
    id: number;
    firstName: string;
    lastName: string;
    birthDate: Date;
    description: string;
    imgPath: string;
    movies: Movie[];
}
