import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { EstabelecimentoModule } from './estabelecimentos/estabelecimento.module';
import { VeiculosModule } from './veiculos/veiculos.module';

@Module({
  imports: [EstabelecimentoModule, VeiculosModule],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule { }
