import { createConnection } from 'typeorm';

export const databaseProviders = [
  {
    provide: 'DATABASE_CONNECTION',
    useFactory: async () => createConnection({
      type: 'mysql',
      host: 'localhost',
      port: 3306,
      username: 'carlos',
      password: '123456',
      database: 'fcamara',
      entities: [
        __dirname + '/../**/*.entity{.ts,.js}',
      ],
      synchronize: false,
    }),
  },
];