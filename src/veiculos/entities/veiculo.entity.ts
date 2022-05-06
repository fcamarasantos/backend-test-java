import { Log } from 'src/logs/entities/log.entity'
import { Estabelecimento } from 'src/estabelecimentos/entities/estabelecimento.entity';
import {
  Entity,
  Column,
  PrimaryColumn,
  OneToMany
} from 'typeorm';


@Entity()
export class Veiculo {
  @PrimaryColumn()
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