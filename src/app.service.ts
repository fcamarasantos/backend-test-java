import { Inject, Injectable } from '@nestjs/common';
import { ConfigService } from '@nestjs/config';

@Injectable()
export class AppService {
  @Inject(ConfigService)
  public config: ConfigService;

  getHello(): string {

    const databaseName: string = this.config.get('DATABASE_NAME');
    console.log({ databaseName });

    return 'Estacionamento FCamara';
  }
}
