import { ApiProperty } from '@nestjs/swagger';

type Veiculo = {
  id: number;
  placa: string;
  tipo: string;
  marca: string;
  modelo: string;
  cor: string;
}

export class CreateEstabelecimentoDto {
  @ApiProperty({ type: Number, description: 'CNPJ como identificador do estabelecimento.' })
  readonly cnpj: string;
  @ApiProperty({ type: String, description: 'Nome do estabelecimento.' })
  readonly nome: string;
  @ApiProperty({ type: Number, description: 'Total de vagas físicas para carros.' })
  readonly total_vagas_carros: number;
  @ApiProperty({ type: Number, description: 'Total de vagas físicas para motos.' })
  readonly total_vagas_motos: number;
  @ApiProperty({ type: String, description: 'Endereço completo do estabelecimento.' })
  readonly endereco: string;
  @ApiProperty({ type: String, description: 'Telefone completo do estabelecimento.' })
  readonly telefone_estabelecimento: string;
}