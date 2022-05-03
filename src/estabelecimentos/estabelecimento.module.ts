import { Module } from '@nestjs/common';
import { EstabelecimentoController } from './estabelecimento.controller';
import { EstabelecimentoService } from './estabelecimento.service';

@Module({
  controllers: [EstabelecimentoController],
  providers: [EstabelecimentoService]
})
export class EstabelecimentoModule { }
