import { Entity, Column, PrimaryColumn } from 'typeorm';


@Entity()
export class Estabelecimento {
  @PrimaryColumn({ type: "varchar", length: 14 })
  cnpj: string;

  @Column()
  nome: string;

  @Column()
  total_vagas_carros: number;

  @Column()
  total_vagas_motos: number;

  @Column()
  endereco: string;

  @Column()
  telefone_estabelecimento: string;
}