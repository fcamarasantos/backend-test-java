type Veiculo = {
  id: number;
  placa: string;
  tipo: string;
  marca: string;
  modelo: string;
  cor: string;
}

export class CreateEstabelecimentoDto {
  readonly cnpj: string;
  readonly nome: string;
  readonly total_vagas_carros: number;
  readonly total_vagas_motos: number;
  readonly endereco: string;
  readonly telefone_estabelecimento: string;
  readonly veiculo: Veiculo;
}