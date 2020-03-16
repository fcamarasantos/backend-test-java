using FluentValidation;
using Management.Domain.Entities;
using System;
using System.Collections.Generic;
using System.Text;

namespace Management.Service.Validators
{
    public class ColumnValidator: AbstractValidator<Column>
    {
        public ColumnValidator()
        {
            RuleFor(c => c)
                .NotNull();

            RuleFor(c => c.description)
                .NotEmpty().WithMessage("Necessario informar a descrição da coluna")
                .NotNull().WithMessage("Necessario informar a descrição da coluna")
                .MaximumLength(30).WithMessage("Descrição da coluna deve conter apenas 30 digitos");
        }
    }
}
