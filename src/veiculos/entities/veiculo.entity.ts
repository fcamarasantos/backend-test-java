import { Log } from 'src/logs/entities/log.entity'
import { Estabelecimento } from 'src/estabelecimentos/entities/estabelecimento.entity';
import {
  Entity,
  Column,
  PrimaryGeneratedColumn,
  OneToMany
} from 'typeorm';


@Entity()
export class Veiculo {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ nullable: false })
  placa: string;

  @Column({ nullable: false })
  tipo: string;

  @Column({ nullable: false })
  marca: string;

  @Column({ nullable: false })
  modelo: string;

  @Column({ nullable: false })
  cor: string;

  @OneToMany(() => Log, veiculos => Veiculo)
  log: Log;
}