import { EstabelecimentosController } from './controller/estabelecimentos.controller';
import { estabelecimentoProviders } from './estabelecimentos.providers';
import { DatabaseModule } from './../data/database.module';
import { Module } from '@nestjs/common';
import { EstabelecimentosService } from './estabelecimentos.service';

@Module({
  imports: [DatabaseModule],
  controllers: [EstabelecimentosController],
  providers: [
    ...estabelecimentoProviders,
    EstabelecimentosService,
  ]
})
export class EstabelecimentosModule { }
