import { EstabelecimentosModule } from './estabelecimentos/estabelecimentos.module';
import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';

@Module({
  imports: [
    EstabelecimentosModule],
  controllers: [AppController],
  providers: [AppService],
})

export class AppModule { }
