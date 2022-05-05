import { Entity, Column, PrimaryColumn } from 'typeorm';


@Entity()
export class Estabelecimento {
  @PrimaryColumn()
  cnpj: number;

  @Column()
  nome: string;

  @Column()
  total_vagas_carros: number;

  @Column()
  total_vagas_motos: number;

  @Column()
  endereco: string;

  @Column()
  endereco_id: number;

  @Column()
  telefone_estabelecimento: number;

}