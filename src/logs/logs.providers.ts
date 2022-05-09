import { Connection } from 'typeorm';
import { Log } from './entities/log.entity';

export const logsProviders = [
  {
    provide: 'LOG_REPOSITORY',
    useFactory: (connection: Connection) => connection.getRepository(Log),
    inject: ['DATABASE_CONNECTION']
  },
];