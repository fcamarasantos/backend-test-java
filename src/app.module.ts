import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { EstabelecimentoModule } from './estabelecimentos/estabelecimento.module';
import { VeiculoModule } from './veiculos/veiculo.module';

@Module({
  imports: [EstabelecimentoModule, VeiculoModule],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule { }
