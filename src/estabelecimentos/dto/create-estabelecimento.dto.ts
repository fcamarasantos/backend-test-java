type Veiculo = {
  id: number,
  placa: string,
  tipo: number,
  marca: number,
  modelo: number,
  cor: number
}

export class CreateEstabelecimentoDto {
  readonly cnpj: string;
  readonly nome: string;
  readonly total_vagas_carros: number;
  readonly total_vagas_motos: number;
  readonly endereco: string;
  readonly endereco_id: number;
  readonly telefone_estabelecimento: number;
}