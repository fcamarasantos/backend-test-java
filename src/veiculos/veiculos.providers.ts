import { Connection } from 'typeorm';
import { Veiculo } from './entities/veiculo.entity';

export const veiculoProviders = [
  {
    provide: 'VEICULO_REPOSITORY',
    useFactory: (connection: Connection) => connection.getRepository(Veiculo),
    inject: ['DATABASE_CONNECTION']
  },
];