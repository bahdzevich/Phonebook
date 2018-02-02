using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

using Microsoft.EntityFrameworkCore;
using news.Models;
using news.ModelConfigurations;
using System.ComponentModel.DataAnnotations.Schema;

namespace news.Repositories
{
    public class CategoryContext : DbContext
    {
        public DbSet<Category> Category { get; set; }

        public DbSet<NewsCategory> NewsCategory { get; set; }

        public CategoryContext(DbContextOptions<CategoryContext> options)
            : base(options)
        {
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.ApplyConfiguration(new NewsCategoryConfiguration());
        }
    }
}
