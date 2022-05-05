import { Estabelecimento } from 'src/estabelecimentos/entities/estabelecimento.entity';
import { Entity, Column, PrimaryGeneratedColumn, ManyToOne } from 'typeorm';


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


  @ManyToOne(type => Estabelecimento, veiculos => Veiculo)
  estabelecimento: Estabelecimento
}