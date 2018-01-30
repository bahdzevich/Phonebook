using Microsoft.EntityFrameworkCore;
using news.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace news.ModelConfigurations
{
    public class NewsCategoryConfiguration : IEntityTypeConfiguration<NewsCategory>
    {
        public void Configure(EntityTypeBuilder<NewsCategory> builder)
        {
            builder.HasKey(newsCategory => new { newsCategory.NewsId, newsCategory.CategoryId });

            builder
                .HasOne(nc => nc.News)
                .WithMany(n => n.NewsCategory)
                .HasForeignKey(nc => nc.NewsId);

            builder
                .HasOne(nc => nc.Category)
                .WithMany(c => c.NewsCategory)
                .HasForeignKey(nc => nc.CategoryId);
        }
    }
}
