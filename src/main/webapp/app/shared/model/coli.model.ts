export interface IColi {
  id?: number;
  description?: string;
  longueur?: number;
  largeur?: number;
  hauteur?: number;
  poids?: number;
}

export class Coli implements IColi {
  constructor(
    public id?: number,
    public description?: string,
    public longueur?: number,
    public largeur?: number,
    public hauteur?: number,
    public poids?: number
  ) {}
}
