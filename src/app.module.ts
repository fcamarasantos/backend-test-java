import { VeiculosModule } from './veiculos/veiculos.module';
import { EstabelecimentosModule } from './estabelecimentos/estabelecimentos.module';
import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';

@Module({
  imports: [
    EstabelecimentosModule,
    VeiculosModule],
  controllers: [AppController],
  providers: [AppService],
})

export class AppModule { }
