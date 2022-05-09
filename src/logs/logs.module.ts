import { LogsController } from './controller/logs.controller';
import { logsProviders } from './logs.providers';
import { DatabaseModule } from '../data/database.module';
import { Module } from '@nestjs/common';
import { LogsService } from './logs.service';

@Module({
  imports: [DatabaseModule],
  controllers: [LogsController],
  providers: [
    ...logsProviders,
    LogsService,
  ]
})
export class LogsModule { }
