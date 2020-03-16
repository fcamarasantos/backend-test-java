using Management.Domain.Entities;
using Management.Domain.Interfaces;
using Management.Infra.Data.Repository;
using Microsoft.Extensions.DependencyInjection;
using System;
using System.Collections.Generic;
using System.Text;

namespace Management.Infra.CrossCutting
{
    public static class DependencyInjectionRepository
    {
        public static void RegisterServices(IServiceCollection services)
        {

        }

        public static void RegisterRepositories(IServiceCollection services)
        {
            services.AddScoped<IGenericRepository<BaseEntity>, GenericRepository<BaseEntity>>();
        }
    }
}
