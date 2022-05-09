import { Connection } from 'typeorm';
import { Estabelecimento } from './entities/estabelecimento.entity';

export const estabelecimentoProviders = [
  {
    provide: 'ESTABELECIMENTO_REPOSITORY',
    useFactory: (connection: Connection) => connection.getRepository(Estabelecimento),
    inject: ['DATABASE_CONNECTION']
  },
];