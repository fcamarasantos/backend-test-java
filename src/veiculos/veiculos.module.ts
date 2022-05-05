import { VeiculosController } from './controller/veiculos.controller';
import { veiculoProviders } from './veiculos.providers';
import { DatabaseModule } from '../data/database.module';
import { Module } from '@nestjs/common';
import { VeiculosService } from './veiculos.service';

@Module({
  imports: [DatabaseModule],
  controllers: [VeiculosController],
  providers: [
    ...veiculoProviders,
    VeiculosService,
  ]
})
export class VeiculosModule { }
