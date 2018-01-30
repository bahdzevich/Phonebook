using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using Microsoft.Extensions.Options;
using news.Repositories;
using Microsoft.EntityFrameworkCore;
using Pivotal.Discovery.Client;

namespace news
{
    public class Startup
    {
        public Startup(IHostingEnvironment env)
        {
            // Set up configuration sources.
            var builder = new ConfigurationBuilder()
                .SetBasePath(env.ContentRootPath)

                // Read in Discovery clients configuration
                .AddJsonFile("appsettings.json")
                   
                .AddEnvironmentVariables();

            Configuration = builder.Build();
        }

 /*       public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }*/

        public IConfiguration Configuration { get; private set; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            String connectionString = "server=localhost;userid=root;pwd=root;port=3306;database=phonebook;sslmode=none;";
            services.AddDbContext<NewsContext>(options => options.UseMySQL(connectionString));
            services.AddDbContext<CategoryContext>(options => options.UseMySQL(connectionString));

            // Add Eureka client.
            services.AddDiscoveryClient(Configuration);

            services.AddMvc();
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IHostingEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            // обработка ошибок HTTP
            app.UseStatusCodePages();

            app.UseStaticFiles();

            app.UseMvc();

            app.UseDiscoveryClient();
        }
    }
}
