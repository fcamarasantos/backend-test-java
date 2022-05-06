import { Log } from 'src/logs/entities/log.entity';
import {
  Entity,
  Column,
  PrimaryColumn,
  JoinColumn,
  OneToMany
} from 'typeorm';


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

  @OneToMany(type => Log, estabelecimento => Estabelecimento)
  logs: Log[];
}