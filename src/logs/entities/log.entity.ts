import { Estabelecimento } from 'src/estabelecimentos/entities/estabelecimento.entity';
import { Veiculo } from 'src/veiculos/entities/veiculo.entity';
import { Entity, Column, PrimaryGeneratedColumn, JoinColumn, OneToOne, OneToMany, ManyToOne } from 'typeorm';


@Entity()
export class Log {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ nullable: false })
  date: Date

  @Column({ nullable: false })
  tipo_evento: 'entrada' | 'saida';

  @ManyToOne(type => Estabelecimento, logs => Log, { eager: true })
  estabelecimento: Estabelecimento;

  @ManyToOne(() => Veiculo, "id", { eager: true })
  veiculo: Veiculo;
}