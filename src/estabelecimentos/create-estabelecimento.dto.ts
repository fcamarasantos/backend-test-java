type Veiculo = {
  id: number,
  placa: string,
  tipo: number,
  marca: number,
  modelo: number,
  cor: number
}

type Endereco = {
  id: number,
  cep: string,
  numero: string
}

export class CreateEstabelecimentoDto {
  readonly id: number;
  readonly veiculos: Veiculo[]
  readonly endereco: Endereco[]
}