import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { EstabelecimentosModule } from './estabelecimentos/estabelecimentos.module';
import { VeiculosModule } from './veiculos/veiculos.module';

@Module({
  imports: [EstabelecimentosModule, VeiculosModule],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
