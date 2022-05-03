type Veiculo = {
  id: number,
  placa: string,
  tipo: number,
  marca: number,
  modelo: number,
  cor: number
}

type Enderecos = {
  id: number,
  cep: string,
  numero: string
}

type Cep = {
  cep: string,
  uf_id: number,
  cidade_id: number,
  bairro_id: number
  rua_id: number
}

export class UpdateEstabelecimentoDto {
  readonly cnpj: string;
  readonly nome: string;
  readonly total_vagas_carros: number;
  readonly total_vagas_motos: number;
  readonly endereco_id: number;
  readonly telefone_estabelecimento: number;
  readonly veiculos: Veiculo[]
}