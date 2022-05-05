import { Veiculo } from 'src/veiculos/entities/veiculo.entity';
import { Entity, Column, PrimaryColumn, OneToMany } from 'typeorm';


@Entity()
export class Estabelecimento {
  @PrimaryColumn({ type: "varchar", length: 14 })
  cnpj: string;

  @Column({ nullable: false })
  nome: string;

  @Column({ nullable: false })
  total_vagas_carros: number;

  @Column({ nullable: false })
  total_vagas_motos: number;

  @Column({ nullable: false })
  endereco: string;

  @Column({ nullable: false })
  telefone_estabelecimento: string;

  @OneToMany(type => Veiculo, estabelecimento => Estabelecimento)
  veiculos: Veiculo[];
}