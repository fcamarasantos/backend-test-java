using Management.Domain.Entities;
using Management.Domain.Interfaces;
using System;
using System.Collections.Generic;
using System.Text;

namespace Management.Infra.Data.Repository
{
    public class GenericRepository<T> : IGenericRepository<T> where T: BaseEntity
    {
        public GenericRepository()
        {
        }

        public ICollection<T> get(int? id = null)
        {
            throw new NotImplementedException();
        }

        public T insert(T entity)
        {
            throw new NotImplementedException();
        }

        public bool remove(int id)
        {
            throw new NotImplementedException();
        }

        public T update(int id, T entity)
        {
            throw new NotImplementedException();
        }
    }
}
