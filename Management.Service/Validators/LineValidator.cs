using FluentValidation;
using Management.Domain.Entities;
using System;
using System.Collections.Generic;
using System.Text;

namespace Management.Service.Validators
{
    public class LineValidator: AbstractValidator<Line>
    {
        public LineValidator()
        {
            RuleFor(u => u)
                .NotNull();

            RuleFor(ty => ty.description)
                .NotEmpty().WithMessage("Necessário informar a descricao da linha.")
                .NotNull().WithMessage("Necessário informar a descricao da linha.")
                .MaximumLength(30).WithMessage("A descrição da linha deve conter apenas 30 digitos");
        }
    }
}
