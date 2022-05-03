import { Test, TestingModule } from '@nestjs/testing';
import { EstabelecimentoService } from './veiculo.service';

describe('EstabelecimentoService', () => {
  let service: EstabelecimentoService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [EstabelecimentoService],
    }).compile();

    service = module.get<EstabelecimentoService>(EstabelecimentoService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
