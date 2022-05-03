import { Test, TestingModule } from '@nestjs/testing';
import { EstabelecimentoController } from './estabelecimento.controller';

describe('EstabelecimentoController', () => {
  let controller: EstabelecimentoController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [EstabelecimentoController],
    }).compile();

    controller = module.get<EstabelecimentoController>(EstabelecimentoController);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });
});
