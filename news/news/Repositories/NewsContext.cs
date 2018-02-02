using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using news.Models;
using news.ModelConfigurations;

namespace news.Repositories
{
    public class NewsContext : DbContext
    {
        public DbSet<News> News { get; set; }

        public DbSet<NewsCategory> NewsCategory { get; set; }

        public DbSet<Category> Category { get; set; }

        public NewsContext(DbContextOptions<NewsContext> options)
            : base(options)
        {
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.ApplyConfiguration(new NewsConfiguration());
            modelBuilder.ApplyConfiguration(new NewsCategoryConfiguration());
        }
    }
}
