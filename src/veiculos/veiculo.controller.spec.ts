import { Test, TestingModule } from '@nestjs/testing';
import { VeiculoController } from './veiculo.controller';

describe('VeiculoController', () => {
  let controller: VeiculoController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [VeiculoController],
    }).compile();

    controller = module.get<VeiculoController>(VeiculoController);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });
});
