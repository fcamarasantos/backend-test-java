import { Module } from '@nestjs/common';
import { VeiculoController } from './veiculo.controller';
import { VeiculoService } from './veiculo.service';

@Module({
  controllers: [VeiculoController],
  providers: [VeiculoService]
})
export class VeiculoModule { }
